package top.ireed.found.words;

import java.util.HashSet;
import java.util.Set;

/**
 * 词
 */
public class Word {

    /**
     * id 词本身
     */
    private String id;
    private String name;
    private Set<String> lift = new HashSet<>();
    //后字数组
    private Set<String> right = new HashSet<>();
    //前后-前数组
    private Set<String> liftOr = new HashSet<>();
    //前后-后数组
    private Set<String> rightOr = new HashSet<>();

    public Word() {
    }

    public Word(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getLift() {
        return lift;
    }

    public void setLift(Set<String> lift) {
        this.lift = lift;
    }

    public Set<String> getRight() {
        return right;
    }

    public void setRight(Set<String> right) {
        this.right = right;
    }

    public Set<String> getLiftOr() {
        return liftOr;
    }

    public void setLiftOr(Set<String> liftOr) {
        this.liftOr = liftOr;
    }

    public Set<String> getRightOr() {
        return rightOr;
    }

    public void setRightOr(Set<String> rightOr) {
        this.rightOr = rightOr;
    }
}
