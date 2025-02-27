package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.FuliEntity;

import com.service.FuliService;
import com.entity.view.FuliView;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 福利
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fuli")
public class FuliController {
    private static final Logger logger = LoggerFactory.getLogger(FuliController.class);

    @Autowired
    private FuliService fuliService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("员工".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("orderBy","id");
        PageUtils page = fuliService.queryPage(params);

        //字典表数据转换
        List<FuliView> list =(List<FuliView>)page.getList();
        for(FuliView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FuliEntity fuli = fuliService.selectById(id);
        if(fuli !=null){
            //entity转view
            FuliView view = new FuliView();
            BeanUtils.copyProperties( fuli , view );//把实体数据重构到view中

            //级联表
            YonghuEntity yonghu = yonghuService.selectById(fuli.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody FuliEntity fuli, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fuli:{}",this.getClass().getName(),fuli.toString());

//        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("员工".equals(role))
//            fuli.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        Wrapper<FuliEntity> queryWrapper = new EntityWrapper<FuliEntity>()
//            .eq("yonghu_id", fuli.getYonghuId())
//            .eq("fuli_name", fuli.getFuliName())
//            .eq("fuli_types", fuli.getFuliTypes())
//            ;
//        logger.info("sql语句:"+queryWrapper.getSqlSegment());
//        FuliEntity fuliEntity = fuliService.selectOne(queryWrapper);
//        if(fuliEntity==null){
            fuli.setInsertTime(new Date());
            fuli.setCreateTime(new Date());
            fuliService.insert(fuli);
            return R.ok();
//        }else {
//            return R.error(511,"表中有相同数据");
//        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FuliEntity fuli, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fuli:{}",this.getClass().getName(),fuli.toString());

//        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("员工".equals(role))
//            fuli.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        //根据字段查询是否有相同数据
//        Wrapper<FuliEntity> queryWrapper = new EntityWrapper<FuliEntity>()
//            .notIn("id",fuli.getId())
//            .andNew()
//            .eq("yonghu_id", fuli.getYonghuId())
//            .eq("fuli_name", fuli.getFuliName())
//            .eq("fuli_types", fuli.getFuliTypes())
//            ;
//        logger.info("sql语句:"+queryWrapper.getSqlSegment());
//        FuliEntity fuliEntity = fuliService.selectOne(queryWrapper);
//        if(fuliEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      fuli.set
            //  }
            fuliService.updateById(fuli);//根据id更新
            return R.ok();
//        }else {
//            return R.error(511,"表中有相同数据");
//        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        fuliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }






}

