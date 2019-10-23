package com.userorder.repoModule.service;

import com.userorder.pojo.OrderDetail;

import java.util.List;

public interface RepoService {

    Integer updateRepo(List<OrderDetail> ods);

}
