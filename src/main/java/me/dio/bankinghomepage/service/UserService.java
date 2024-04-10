package me.dio.bankinghomepage.service;

import me.dio.bankinghomepage.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends CrudService<Long, User> {
}
