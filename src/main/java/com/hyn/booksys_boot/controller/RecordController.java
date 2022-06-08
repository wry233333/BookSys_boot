package com.hyn.booksys_boot.controller;


import com.github.pagehelper.PageInfo;
import com.hyn.booksys_boot.service.RecordService;
import com.wry333.booksys_boot.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 请求方法
     * get请求
     * url:/localhost/record/page/(这里填分页页数)
     *
     * @param page
     * @return
     */

    @GetMapping("/page/{page}")
    @ResponseBody
    public PageInfo<Record> test(@PathVariable String page) {
        PageInfo<Record> recordPageInfo = recordService.getAllRecord(Integer.parseInt(page));
        return recordPageInfo;
    }

    /**
     * 请求方法
     * post请求
     * 在request里加上json封装的{id ，b_id ，time}
     *
     * @param map
     * @return
     */
    @PostMapping("/add")
    public boolean addRecord(@RequestBody Map map) {
        recordService.saveRecord(map);
        return false;
    }


    /**
     * @param uid
     * @return
     */
    @GetMapping("/notify/{uid}")
    public boolean notify_user(@PathVariable String uid) {
        recordService.sendNotifyEmail(Long.parseLong(uid));
        return true;
    }

    @GetMapping("/delete/{l_id}")
    public boolean delete(@PathVariable String l_id) {
        recordService.removeRecord(Long.parseLong(l_id));
        return true;
    }

    @PostMapping("/update")
    public boolean updateRecord(@RequestBody Record record) {
        recordService.updateRecord(record);
        return true;
    }

    @GetMapping("/page/{u_id}/{page}")
    @ResponseBody
    public PageInfo<Record> userpage(@PathVariable String page, @PathVariable String u_id) {
        PageInfo<Record> recordPageInfo = recordService.getUserRecord(Integer.parseInt(page), Long.parseLong(u_id));
        return recordPageInfo;
    }

    @PostMapping("/delay")
    public boolean delay(@RequestBody Map map) {
        recordService.delay(map);
        return true;
    }

}
