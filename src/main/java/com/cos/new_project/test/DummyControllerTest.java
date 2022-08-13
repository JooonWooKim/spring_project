package com.cos.new_project.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.new_project.model.RoleType;
import com.cos.new_project.model.User;
import com.cos.new_project.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired	//의존성 주입(DI)
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User>list(){
		return userRepository.findAll();
	}
	
	//paging
	//한 페이지당 2건의 데이터를 리턴받기
	@GetMapping("/dummy/user")
	public List<User>pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User>users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있다.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자가 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다.");
			}
		});
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		System.out.println("id:"+user.getId());
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
