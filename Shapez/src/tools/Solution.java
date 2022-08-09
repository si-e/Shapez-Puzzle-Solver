package tools;

import game.Entity;

public class Solution {

    private Entity[] entities;
    private String s;

    public Solution(Entity[] entities) {
        this.entities = entities;
    }

    public Solution(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
