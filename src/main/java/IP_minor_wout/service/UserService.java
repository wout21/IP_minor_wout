package IP_minor_wout.service;


import IP_minor_wout.dto.CreateUserDTO;
import IP_minor_wout.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	UserDTO createUser(CreateUserDTO userDTO);
}
