/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dogp.zoocount;

/**
 * Objects implementing this interface can tell it's listeners weather it's data
 * has changed.
 *
 * @author jonas
 */
public interface Smudgifier {
    public void smudge(boolean dirty);
}
