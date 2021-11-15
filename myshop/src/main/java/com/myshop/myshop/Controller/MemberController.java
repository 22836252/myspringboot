package com.myshop.myshop.Controller;

import com.myshop.myshop.Service.MemberService;
import com.myshop.myshop.entity.Member;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    MemberService MemberService;
    @ApiOperation("取得所有代辦事項列表")
    @ApiResponses({
            @ApiResponse(code=401,message="沒有權限"),
            @ApiResponse(code=404,message="找不到路徑")
    })
    @GetMapping("/Members")
    public ResponseEntity getMembers() {
        Iterable<Member> MemberList = MemberService.getMembers();
        return ResponseEntity.status(HttpStatus.OK).body(MemberList);
    }

    @GetMapping("/Members/{id}")
    public Optional<Member> getMember(@PathVariable Integer id) {
        Optional<Member> Member = MemberService.findById(id);
        return Member;
    }

    @PostMapping("/Members/1")
    public ResponseEntity createMember(@RequestBody Member Member) {
        Integer rlt = MemberService.createMember(Member);
        return ResponseEntity.status(HttpStatus.CREATED).body(rlt);
    }

//    @PutMapping("/Members/{id}")
//    public ResponseEntity upadteMember(@PathVariable Integer id, @RequestBody Member Member) {
//        Boolean rlt = MemberService.updateMember(id ,Member);
//        if (!rlt) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status 欄位不能為空");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body("");
//    }

    @DeleteMapping("/Members/{id}")
    public ResponseEntity deleteMember(@PathVariable Integer id) {
        Boolean rlt = MemberService.deleteMember(id);
        if (!rlt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id 不存在");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

}
