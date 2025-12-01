package com.feisuanyz.common.service;

import com.feisuanyz.common.dto.*;
import com.feisuanyz.common.dto.CustomCoverRequest;
import com.feisuanyz.common.dto.FrameCoverRequest;
import com.feisuanyz.common.dto.InitUploadRequest;
import com.feisuanyz.common.dto.InitUploadResponse;
import com.feisuanyz.common.dto.MergeUploadRequest;
import com.feisuanyz.common.dto.MergeUploadResponse;
import com.feisuanyz.common.dto.QueryUploadStatusRequest;
import com.feisuanyz.common.dto.QueryUploadStatusResponse;
import com.feisuanyz.common.dto.UploadChunkRequest;
import com.feisuanyz.common.response.RestResult;

/**
 * 视频上传服务接口
 *
 * @author tianchunxu
 */
public interface VideoUploadService {

    /**
     * 初始化视频分片上传
     *
     * @param request 初始化上传请求参数
     * @return RESTful API响应结果
     */
    RestResult<InitUploadResponse> initUpload(InitUploadRequest request);

    /**
     * 上传单个视频分片
     *
     * @param request 上传分片请求参数
     * @return RESTful API响应结果
     */
    RestResult<Void> uploadChunk(UploadChunkRequest request);

    /**
     * 完成分片上传合并
     *
     * @param request 合并请求参数
     * @return RESTful API响应结果
     */
    RestResult<MergeUploadResponse> mergeUpload(MergeUploadRequest request);

    /**
     * 查询视频上传状态
     *
     * @param request 查询状态请求参数
     * @return RESTful API响应结果
     */
    RestResult<QueryUploadStatusResponse> queryUploadStatus(QueryUploadStatusRequest request);

    /**
     * 自定义上传视频封面
     *
     * @param request 自定义封面请求参数
     * @return RESTful API响应结果
     */
    RestResult<Void> customCover(CustomCoverRequest request);

    /**
     * 从视频帧选取封面
     *
     * @param request 选取封面请求参数
     * @return RESTful API响应结果
     */
    RestResult<Void> frameCover(FrameCoverRequest request);
}