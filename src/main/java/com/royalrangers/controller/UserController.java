package com.royalrangers.controller;

import com.dropbox.core.DbxException;
import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.user.AvatarUrlDTO;
import com.royalrangers.dto.user.IdsDTO;
import com.royalrangers.dto.user.UserDTO;
import com.royalrangers.exception.UserRepositoryException;
import com.royalrangers.service.DropboxService;
import com.royalrangers.service.UserProfileService;
import com.royalrangers.service.UserService;
import com.royalrangers.utils.ResponseBuilder;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserProfileService profileService;

    @Autowired
    private UserService userService;

    @Autowired
    private DropboxService dropboxService;

    @GetMapping
    @ApiOperation(value = "Get user info")
    public ResponseResult getAuthenticatedUserDetail() {

        String username = userService.getAuthenticatedUserEmail();
        log.info("Get details for user " + username);

        return ResponseBuilder.success(profileService.getUserDetailByEmail(username));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get user info (for admin)")
    public ResponseResult getUserDetailById(@PathVariable("id") Long id) {

        try {
            log.info("Get details for user id " + id);
            return ResponseBuilder.success(profileService.getUserDetailById(id));

        } catch (UserRepositoryException e){

            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @GetMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get users for approve (for admin)")
    public ResponseResult getUserToApprove(@PathVariable("id") Long platoonId){
        List<UserDTO> usersForApprove = userService.getUsersForApprove(platoonId);
        return ResponseBuilder.success(usersForApprove);
    }

    @PostMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Approve users after registration (for admin)")
    public ResponseResult approveUser(@RequestBody IdsDTO param) {
        List<Long> ids = param.getIds();
        userService.approveUsers(ids);
        return ResponseBuilder.success("Users successfully approved.");
    }

    @PostMapping("/reject")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Reject user after registration (for admin)")
    public ResponseResult rejectUser(@RequestBody IdsDTO param) {
        List<Long> ids = param.getIds();
        userService.rejectUsers(ids);
        return ResponseBuilder.success("Users successfully rejected.");
    }

    @PutMapping
    @ApiOperation(value = "Update user")
    public ResponseResult updateAuthorizedUser(@RequestBody UserDTO update) {

        String email = userService.getAuthenticatedUserEmail();

        userService.updateUserByEmail(email, update);
        log.info("Update user " + email);

        return ResponseBuilder.success(String.format("User %s successful updated", email));
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update user (for admin)")
    public ResponseResult updateUserById(@PathVariable("id") Long id, @RequestBody UserDTO userUpdate) {

        try {
            userService.updateUserById(id, userUpdate);
            log.info("Update user with id %d " + id);

            return ResponseBuilder.success(String.format("User with id %d successful updated", id));

        } catch (UserRepositoryException e){
            return ResponseBuilder.fail(e.getMessage());
        }
    }

    @PostMapping("/avatar")
    @ApiOperation(value = "Upload and set user avatar")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {

        try {
            String avatarUrl = dropboxService.avatarUpload(file);
            log.info("Set user avatar public URL: " +avatarUrl);

            userService.setUserAvatarUrl(avatarUrl);

            return ResponseBuilder.success("avatarUrl", avatarUrl);

        } catch (IOException | DbxException e) {
            return  ResponseBuilder.fail(e.getMessage());
        }
    }

    @DeleteMapping("/avatar")
    @ApiOperation(value = "Delete avatar picture")
    //TODO Get rid of "one-string DTOs"
    public ResponseResult delete(@RequestBody AvatarUrlDTO url) {
        String avatarUrl = url.getAvatarUrl();
        try {
            dropboxService.deleteAvatar(avatarUrl);
            log.info("Delete avatar: " + avatarUrl);

            return ResponseBuilder.success(avatarUrl + " deleted.");

        } catch (DbxException e) {
            return ResponseBuilder.fail(e.getMessage());
        }
    }
}
