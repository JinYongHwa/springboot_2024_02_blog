package kr.ac.mjc.blog.service;

import kr.ac.mjc.blog.domain.User;
import kr.ac.mjc.blog.dto.UserRequest;
import kr.ac.mjc.blog.dto.UserResponse;
import kr.ac.mjc.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponse join(UserRequest request){
        Optional<User> result=userRepository.findById(request.getId());
        UserResponse response=new UserResponse();
        if(result.isEmpty()){   //회원가입 하려는 아이디로 가입된 회원이 없는경우 -> 회원가입 가능
            User user=new User();
            user.setId(request.getId());
            user.setPassword(request.getPassword());
            userRepository.save(user);  //DB 에 insert
            response.setSuccess(true);
            response.setMessage("회원가입이 완료되었습니다");
        }
        else{       //이미 같은 아이디로 가입된 회원이 있는경우 -> 회원가입 불가
            response.setSuccess(false);
            response.setMessage("이미 가입된 아이디입니다");
        }
        return response;
    }

    public UserResponse login(UserRequest request){
        Optional<User> result=userRepository.findOneByIdAndPassword(request.getId(),
                request.getPassword());
        UserResponse response=new UserResponse();
        if(result.isEmpty()){   //아이디 또는 패스워드가 틀림 -> 로그인실패
            response.setSuccess(false);
            response.setMessage("아이디 또는 패스워드가 틀립니다");
        }
        else{   //아이디와 패스워드가 둘다 일치 -> 로그인 성공
            response.setSuccess(true);
            response.setMessage("로그인이 완료되었습니다");
            response.setUser(result.get());
        }
        return response;
    }
}
