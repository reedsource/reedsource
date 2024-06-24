package top.ireed.found.words;

import java.util.HashSet;
import java.util.Set;

/**
 * 词
 */
public class Word {

    public Word() {
    }

    public Word(String name) {
        this.name = name;
    }

    private String id;
    private String name;

    private String lifts;
    //后字数组
    private String rights;
    //前后-前数组
    private String liftOrs;
    //前后-后数组
    private String rightOrs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifts() {
        return lifts;
    }

    public void setLifts(String lifts) {
        this.lifts = lifts;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getLiftOrs() {
        return liftOrs;
    }

    public void setLiftOrs(String liftOrs) {
        this.liftOrs = liftOrs;
    }

    public String getRightOrs() {
        return rightOrs;
    }

    public void setRightOrs(String rightOrs) {
        this.rightOrs = rightOrs;
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

    private Set<String> lift = new HashSet<>();
    //后字数组
    private Set<String> right = new HashSet<>();
    //前后-前数组
    private Set<String> liftOr = new HashSet<>();
    //前后-后数组
    private Set<String> rightOr = new HashSet<>();
}
