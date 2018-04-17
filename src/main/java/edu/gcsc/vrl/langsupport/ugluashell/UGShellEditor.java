/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.gcsc.vrl.langsupport.ugluashell;

import eu.mihosoft.vrl.annotation.ComponentInfo;
import eu.mihosoft.vrl.annotation.OutputInfo;
import eu.mihosoft.vrl.annotation.ParamInfo;
import java.io.Serializable;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
@ComponentInfo(name="UG Shell Lua Editor", category="UG4/Lua")
public class UGShellEditor implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @OutputInfo(style = "silent", name = " ")
    public String run(@ParamInfo(name=" ", style="ugluashell-code") String code) {
        return code;
    }
    
}
