package ru.sigma.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sigma.dao.UserDao;
import ru.sigma.domain.TestResult;
import ru.sigma.domain.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserDao userDao;
    private final MessageService messageService;
    private final InputService inputService;
    
    @Override
    public User createUser() {
        messageService.printMessage("message.enter-name");
        String name = inputService.inputData();
        messageService.printMessage("message.enter-surname");
        String surname = inputService.inputData();
        return userDao.createUser(name, surname);
    }
    
    @Override
    public String getPrintableUser(User user) {
        return user.getName() + " " + user.getSurname();
    }
    
    @Override
    public void addTestResult(User user, int testResult) {
        user.getResults().add(new TestResult(testResult));
    }
    
    @Override
    public int getLastTestResult(User user) {
        return user.getResults().get(user.getResults().size() - 1).getResult();
    }
}