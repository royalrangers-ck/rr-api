package com.royalrangers.model;

public class Views {

    public interface Common {}
    public interface Achievement extends Common {}
    public interface AchievementUser extends Achievement {}
    public interface Profile extends Achievement {}
    public interface Public extends Profile{}
    public interface Internal extends Profile {}
}
