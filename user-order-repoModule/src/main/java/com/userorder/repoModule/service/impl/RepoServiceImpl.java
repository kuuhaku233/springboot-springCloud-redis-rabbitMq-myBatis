package com.userorder.repoModule.service.impl;

import com.userorder.pojo.OrderDetail;
import com.userorder.repoModule.mapper.RepoMapper;
import com.userorder.repoModule.service.RepoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {
    @Resource
    private RepoMapper repoMapper;

    @Override
    public Integer updateRepo(List<OrderDetail> ods) {
        for (OrderDetail od : ods) {
             repoMapper.updateRepo(od.getNumber(), od.getProduct_id());
        }
        return null;
    }
}
