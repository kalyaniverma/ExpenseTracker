package com.example.backend.Service;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Entity.User;
import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Model.UserVerificationRequest;
import com.example.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserSessionManager userSessionManager;

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository, UserSessionManager userSessionManager) {
        this.userRepository = userRepository;
        this.userSessionManager = userSessionManager;
    }

    public boolean createUser(UserCreationRequest request){

        try{
            // Creating User entity object
            User user = new User();

            //Extracting user information from request variable
            String firstname = request.getFirstname();
            String lastname = request.getLastname();
            String email = request.getEmail();
            String password = request.getPassword();

            //Storing the extracted information in the User table via user object created
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(password);

            // Save the user to the database
            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public Boolean verifyUser(UserVerificationRequest request){

        try {
            //Extracting login information of user from request variable
            String email = request.getEmail();
            String password = request.getPassword();

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                if (user.getPassword().equals(password)){
                    userSessionManager.setCurrentUserId(user.getId());
                    return true;
                }
                else return false;
            }
            else return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//    public Boolean VERIFY2(UserVerificationRequest request){
//        try {
//            //Extracting login information of user from request variable
//            String email = request.getEmail();
//            //String password = request.getPassword();
//            Optional<User> optionalUser = userRepository.findByEmail(email);
//            if(optionalUser.isPresent()){
//                User user = optionalUser.get();
//                //if (user.getPassword().equals(password)){
//                    //userSessionManager.setCurrentUserId(user.getId());
//                    return true;
//                }
//                else return false;
//            //}
//            //else return false;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
