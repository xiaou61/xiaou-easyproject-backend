package com.xiaou.xiaoueasyprojectbackend.module.support.music.controller;


import com.xiaou.xiaoueasyprojectbackend.module.support.music.model.MusicDetailInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.model.MusicListInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.model.MusicSimpleInfo;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.param.MusicCreateOrUpdateRequestBody;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.param.MusicDeleteRequestBody;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.param.MusicDetailRequestBody;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.param.PageMusicQueryRequestParam;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.service.impl.MusicServiceImpl;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.util.JsonUtil;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.vo.LayuiPageVO;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.vo.MusicInfoLinkDetailVo;
import com.xiaou.xiaoueasyprojectbackend.module.support.music.vo.RespVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/admin")
@RestController
public class MusicInfoController {
	
	@Resource
	private MusicServiceImpl musicService;

	/**
	 * 导出音乐
	 * @return
	 */
	@PostMapping("/exportMusic")
	public RespVO<MusicListInfo> exportMusic() {
		return RespVO.success(musicService.exportMusic());
	}

	/**
	 * 保存或更新音乐
	 * @param requestBody
	 * @return
	 */
	@PostMapping("/createOrUpdateMusic")
	public RespVO<Void> createOrUpdateMusic(@Validated @RequestBody MusicCreateOrUpdateRequestBody requestBody) {
		log.info("requestBody = {}", JsonUtil.toString(requestBody));
		musicService.createOrUpdate(requestBody);
		return RespVO.success();
	}

	/**
	 * 分页查询音乐
	 * @param param
	 * @return
	 */
	@PostMapping("/pageMusic")
	public LayuiPageVO<MusicSimpleInfo> pageMusic(@RequestBody PageMusicQueryRequestParam param) {
		return musicService.pageMusic(param);
	}

	/**
	 * 查询音乐详情
	 * @param requestBody
	 * @return
	 */
	@PostMapping("/detailMusic")
	public RespVO<MusicDetailInfo> detailMusic(@Validated @RequestBody MusicDetailRequestBody requestBody) {
		log.info("requestBody = {}", JsonUtil.toString(requestBody));
		return RespVO.success(musicService.getMusicDetailInfoById(requestBody.getId()));
	}

	/**
	 * 获取音乐来源配置
	 * @return
	 */
	@GetMapping("/getLinkList")
	public RespVO<List<MusicInfoLinkDetailVo>> getLinkList() {
		return RespVO.success(musicService.getLinkList());
	}

	/**
	 * 删除音乐
	 * @param requestBody
	 * @return
	 */
	@PostMapping("/deleteMusic")
	public RespVO<Boolean> deleteMusic(@Validated @RequestBody MusicDeleteRequestBody requestBody) {
		log.info("requestBody = {}", JsonUtil.toString(requestBody));
		return RespVO.success(musicService.deleteMusic(requestBody));
	}

}
