/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gcsc.vrl.langsupport.ugluashell;

import eu.mihosoft.vrl.lang.visual.EditorConfiguration;
import eu.mihosoft.vrl.lang.visual.EditorProvider;
import eu.mihosoft.vrl.reflection.VisualCanvas;
import eu.mihosoft.vrl.system.InitPluginAPI;
import eu.mihosoft.vrl.system.PluginAPI;
import eu.mihosoft.vrl.system.PluginDependency;
import eu.mihosoft.vrl.system.PluginIdentifier;
import eu.mihosoft.vrl.system.VPluginAPI;
import eu.mihosoft.vrl.system.VPluginConfigurator;
import eu.mihosoft.vrl.visual.Disposable;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 *
 * @author Michael Hoffer <info@michaelhoffer.de>
 */
public class LuaPluginConfigurator extends VPluginConfigurator {

    public LuaPluginConfigurator() {
        //specify the plugin name and version
        setIdentifier(new PluginIdentifier("UGLuaShell-Support", "0.1"));

       // optionally allow other plugins to use the api of this plugin
        // you can specify packages that shall be
        // exported by using the exportPackage() method:
        //
        // exportPackage("com.your.package");
        // describe the plugin
        setDescription("Adds editor support for the LUA programming language.");

        // copyright info
        setCopyrightInfo("UGLuaShell-Support",
                "(c) G-CSC",
                "www.gcsc.uni-frankfurt.de", "LGPL", "License Text...");

        // specify dependencies
        addDependency(new PluginDependency("VRL", "0.4.3.2.3", "x"));
        addDependency(new PluginDependency("VRL-UG", "0.2", "0.x"));
        addDependency(new PluginDependency("VRL-UG-API", "0.2", "0.x"));
    }

    @Override
    public void register(PluginAPI api) {

        // register plugin with canvas
        if (api instanceof VPluginAPI) {
            final VPluginAPI vapi = (VPluginAPI) api;

           // Register visual components:
            //
            // Here you can add additional components,
            // type representations, styles etc.
            //
            // ** NOTE **
            //
            // To ensure compatibility with future versions of VRL,
            // you should only use the vapi or api object for registration.
            // If you directly use the canvas or its properties, please make
            // sure that you specify the VRL versions you are compatible with
            // in the constructor of this plugin configurator because the
            // internal api is likely to change.
            //
            // examples:
            //
            // vapi.addComponent(MyComponent.class);
            // vapi.addTypeRepresentation(MyType.class);
            vapi.addEditorConfiguration(new EditorConfiguration() {
                
                private EditorConfiguration config = this;

                @Override
                public void init(VisualCanvas vc) {
                    // TODO init completion provider
                }

                @Override
                public void configure(RSyntaxTextArea editor) {
                    editor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LUA);
                    editor.setCodeFoldingEnabled(true);
                    editor.setTabsEmulated(true);
                    editor.setTabSize(2);

//                    final VAutoCompletion ac = new VAutoCompletion(cp);
//                    ac.addReplacementRule(new AddImportReplacementRule());
//                    ac.setChoicesWindowSize(600, 250);
//                    ac.install(editor);
//                    ac.setTriggerKey(KeyStroke.getKeyStroke(
//                            KeyEvent.VK_SPACE,
//                            KeyEvent.CTRL_DOWN_MASK | KeyEvent.ALT_DOWN_MASK));

                    vapi.getCanvas().addDisposable(new Disposable() {

                        @Override
                        public void dispose() {
//                            ac.uninstall();
                            EditorProvider.removeConfiguration(config);
                        }
                    });
                }

                @Override
                public String getLanguage() {
                    return "ugluashell";
                }
            });
            vapi.addTypeRepresentation(InputCodeType.class);
            vapi.addComponent(LuaEditor.class);
            vapi.addComponent(UGShellEditor.class);
            vapi.addComponent(LuaInterpreter.class);
            vapi.addComponent(UGLuaShell.class);
        }
    }

    @Override
    public void unregister(PluginAPI api) {
        // nothing to unregister
    }

    @Override
    public void init(InitPluginAPI iApi) {
        // nothing to init
    }
}
