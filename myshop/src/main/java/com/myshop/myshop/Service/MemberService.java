package com.myshop.myshop.Service;

import com.myshop.myshop.dao.MemberDao;
import com.myshop.myshop.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberService {

        @Autowired
        MemberDao MemberDao;

        public Iterable<Member> getMembers() {
            return MemberDao.findAll();
        }

        public Integer createMember(Member Member) {
            Member rltMember = MemberDao.save(Member);
            return rltMember.getId();
        }

//        public Boolean updateMember(Integer id,Member Member) {
//            Optional<Member> isExistMember = findById(id);
//            if (! isExistMember.isPresent()) {
//                return false;
//            }
//            Member newMember = isExistMember.get();
//            if (Member.getStatus() == null) {
//                return false;
//            }
//            newMember.setStatus(Member.getStatus());
//            MemberDao.save(newMember);
//            return true;
//        }

        public Optional<Member> findById(Integer id) {
            Optional<Member> Member = MemberDao.findById(id);
            return Member;
        }

        public Boolean deleteMember(Integer id) {
            Optional<Member> findMember = findById(id);
            if (!findMember.isPresent()) {
                return false;
            }
            MemberDao.deleteById(id);
            return true;
        }
}
