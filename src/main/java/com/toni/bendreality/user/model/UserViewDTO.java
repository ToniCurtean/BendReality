package com.toni.bendreality.user.model;

public record UserViewDTO(Integer id,String firstName,String lastName,String phoneNumber,String email,String password,UserRole role) {
}
