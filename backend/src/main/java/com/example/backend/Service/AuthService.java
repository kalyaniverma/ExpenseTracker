package com.example.backend.Service;

import com.example.backend.Configuration.UserSessionManager;
import com.example.backend.Entity.User;
import com.example.backend.Model.UserCreationRequest;
import com.example.backend.Model.UserVerificationRequest;
import com.example.backend.Repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthService {
    private static final Logger logger = LogManager.getLogger(AuthService.class);

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
            logger.info("User {} {} Created Successfully :)", firstname, lastname);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error("Couldn't create user !!");
            return false;
        }

    }

    public String verifyUser(UserVerificationRequest request){

        try {
            //Extracting login information of user from request variable
            String email = request.getEmail();
            String password = request.getPassword();

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                if (user.getPassword().equals(password)){
                    userSessionManager.setCurrentUserId(user.getId());
                    logger.info("User with email: {} Verified Successfully :)", email);
                    return "Logged In successfully";
                }

                else{
                    logger.error("Invalid Credentials !!");
                    return "Invalid Credentials !!";
                }
            }
            else{
                logger.error("User doesn't exist !!");
                return "User does not exist !!";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Transactional // Ensure that the delete operation is part of the same transaction as the test
    public void cleanup(String email) {
        // Find the user by email and delete it
        userRepository.deleteByEmail(email);
    }
}
