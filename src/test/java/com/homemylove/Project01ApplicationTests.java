package com.homemylove;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.homemylove.convert.UserVoConvert;
import com.homemylove.entities.Menu;
import com.homemylove.entities.User;
import com.homemylove.entities.vo.UserVo;
import com.homemylove.mapper.MenuMapper;
import com.homemylove.service.UserService;
import io.swagger.models.auth.In;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@SpringBootTest
class Project01ApplicationTests {

    @Resource
    private MenuMapper menuMapper;
    @Test
    void contextLoads() {
        String path = Project01ApplicationTests.class.getClassLoader().getResource("data.json").getPath();
        String s = readJsonFile(path);
        JSONObject jobj = JSON.parseObject(s);

        JSONArray array = jobj.getJSONArray("data");

        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            Menu menu = new Menu();
            Long menuid = ((Integer)jsonObject.get("menuid")).longValue();
            String icon =(String) jsonObject.get("icon");
            String menuname =(String) jsonObject.get("menuname");
            String hasThird =(String) jsonObject.get("hasThird");
            String url =(String) jsonObject.get("url");
            menu.setMenuId(menuid);
            menu.setIcon(icon);
            menu.setMenuName(menuname);
            menu.setHasThird(hasThird);
            menu.setUrl(url);
            menuMapper.insert(menu);

            JSONArray menus = jsonObject.getJSONArray("menus");

            for (int j = 0; j < menus.size(); j++) {
                JSONObject child = (JSONObject)menus.get(j);
                Long childId =((Integer) child.get("menuid")).longValue();
                icon =(String) child.get("icon");
                menuname =(String) child.get("menuname");
                hasThird =(String) child.get("hasThird");
                url =(String) child.get("url");
                menu.setMenuId(childId);
                menu.setIcon(icon);
                menu.setMenuName(menuname);
                menu.setHasThird(hasThird);
                menu.setUrl(url);
                menu.setPid(menuid);
                menuMapper.insert(menu);
            }
        }

    }

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}




