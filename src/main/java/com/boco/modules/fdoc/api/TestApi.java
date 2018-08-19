package com.boco.modules.fdoc.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.boco.common.json.BaseJsonVo;
import com.boco.common.utils.JsonUtils;

@RestController
@RequestMapping(value = "/test", produces = "text/json;charset=UTF-8")
public class TestApi {
	
	@RequestMapping(value = "/testUpload", method = RequestMethod.POST)
	public String updateUserReport(@RequestParam String description,@RequestParam String timestamp,@RequestParam String operator,
			@RequestParam("file") CommonsMultipartFile file[], HttpServletRequest request) {
		System.out.println(description);
		System.out.println(timestamp);
		System.out.println(operator);
		for (int i = 0; i < file.length; i++) {
			System.out.println(file[i].getOriginalFilename() + "------" + file[i]);
		}
		return JsonUtils.getJson(BaseJsonVo.success(null));
	}
}
