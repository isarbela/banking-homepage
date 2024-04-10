package me.dio.bankinghomepage.service.impl;

import me.dio.bankinghomepage.domain.model.User;
import me.dio.bankinghomepage.domain.repository.UserRepository;
import me.dio.bankinghomepage.service.UserService;
import me.dio.bankinghomepage.service.exception.BusinessException;
import me.dio.bankinghomepage.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private static final Long UNCHANGEABLE_ID = 1L;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public User create(User user) {
        ofNullable(user).orElseThrow(() -> new BusinessException("User to be created must not be null"));
        ofNullable(user.getAccount()).orElseThrow(() -> new BusinessException("User account to be created must not be null"));
        ofNullable(user.getCard()).orElseThrow(() -> new BusinessException("User card to be created must not be null"));

        this.validateChangeableId(user.getId(), "created");

        if(userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("Account number already exists");
        }

        if(userRepository.existsByCardNumber(user.getCard().getNumber())) {
            throw new BusinessException("Card number already exists");
        }

        return this.userRepository.save(user);
    }

    @Transactional
    public User update(Long id, User user) {
        this.validateChangeableId(id, "updated");
        User dbUser = findById(id);
        if (!dbUser.getId().equals(user.getId())) {
            throw new BusinessException("User id does not match");
        }
        dbUser.setAccount(user.getAccount());
        dbUser.setCard(user.getCard());
        dbUser.setName(user.getName());
        dbUser.setFeatures(user.getFeatures());
        dbUser.setNews(user.getNews());

        return this.userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        User user = findById(id);
        this.userRepository.delete(user);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_ID.equals(id))
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_ID, operation));
    }
}
