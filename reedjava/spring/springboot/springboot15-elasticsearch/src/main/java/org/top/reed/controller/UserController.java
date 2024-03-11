package org.top.reed.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.top.reed.constant.Constant;
import org.top.reed.document.EsDocument;
import org.top.reed.dto.UserCityDTO;
import org.top.reed.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能简述:〈es Controller〉
 *
 * @author reedsource
 * date 2024/3/8 16:58
 * reedsource@189.cn
 */
@RestController
@RequestMapping("/es")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 创建索引
     */
    @GetMapping("/createIndex")
    public ResponseEntity<Boolean> createIndex(@RequestParam(value = "index", defaultValue = Constant.INDEX) String index) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUserIndex(index));
    }

    /**
     * 删除索引
     */
    @GetMapping("/deleteIndex")
    public ResponseEntity<Boolean> deleteIndex(@RequestParam(value = "index", defaultValue = Constant.INDEX) String index) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.deleteUserIndex(index));
    }

    /**
     * 新增文档
     */
    @GetMapping("/addDocument")
    public ResponseEntity<Boolean> addDocument() throws Exception {
        EsDocument document = new EsDocument();
        document.setId("20001");
        document.setName("姓名1");
        document.setSex("性别1");
        document.setAge(20);
        document.setCity("北京");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUserDocument(document));
    }

    /**
     * 批量新增文档
     */
    @GetMapping("/addDocumentList")
    public ResponseEntity<Boolean> addDocumentList() throws Exception {
        List<EsDocument> documentList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            EsDocument document = new EsDocument();
            document.setId(String.valueOf(10000 + i));
            document.setName("批量姓名" + i);
            document.setSex("批量性别" + i);
            document.setAge(200 + i);
            document.setCity("北京");
            documentList.add(document);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.bulkCreateUserDocument(documentList));
    }

    /**
     * 删除文档
     *
     * @param id id
     */
    @GetMapping("/deleteDocument")
    public String deleteDocument(@RequestParam String id) throws Exception {
        return userService.deleteUserDocument(id);
    }

    /**
     * 更新文档
     */
    @GetMapping("/updateDocument")
    public ResponseEntity<Boolean> updateDocument(@RequestParam String id) throws Exception {
        EsDocument document = new EsDocument();
        document.setId(id);
        document.setName("更新后姓名");
        document.setSex("更新后性别");
        document.setAge(20);
        document.setCity("北京");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUserDocument(document));
    }

    /**
     * 获取文档
     *
     * @param id id
     */
    @GetMapping("/getUserDocument")
    public EsDocument getUserDocument(@RequestParam String id) throws Exception {
        return userService.getUserDocument(id);
    }

    /**
     * 查询列表
     */
    @GetMapping("/getUserList")
    public List<EsDocument> getUserList() throws Exception {
        return userService.getUserList();
    }

    /**
     * 城市聚合
     */
    @GetMapping("/aggregationsSearchUser")
    public List<UserCityDTO> aggregationsSearchUser() throws Exception {
        return userService.aggregationsSearchUser();
    }

    /**
     * 根据搜索条件
     *
     * @param city 城市 条件
     */
    @GetMapping("/searchUserByCity")
    public List<EsDocument> searchUserByCity(@RequestParam(value = "city") String city) throws Exception {
        return userService.searchUserByCity(city);
    }
}
