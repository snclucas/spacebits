import asg.cliche.Command;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;

import java.io.IOException;

import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;

public class CommandRunner implements ShellDependent {
    
    private String name;
    
    Spacecraft spacecraft;
    
    public CommandRunner(String name) {
        this.name = name;
        
       
        this.spacecraft =  SpacecraftFactory.getSpacecraft("Shuttle");
        spacecraft.online();
    }

    private EnginesShell enginesShell;
    private CommandRunner right;
    
    @Command
    public void setName(String name) {
        this.name = name;
    }
    
    @Command
    public String getName() {
        return name;
    }
    
    @Command
    public void engines() throws IOException {
        if (enginesShell == null) {
        	enginesShell = new EnginesShell("Engines", spacecraft);
        }
        ShellFactory.createSubshell(enginesShell.name, theShell, "z", enginesShell)
                .commandLoop();
    }

    @Command
    public void sensors() throws IOException {
        if (right == null) {
            right = new CommandRunner("sensors");
        }

        // And then a miracle occurs...
        ShellFactory.createSubshell(right.name, theShell, "Right", right)
                .commandLoop();
    }

    private Shell theShell;

    public void cliSetShell(Shell theShell) {
        this.theShell = theShell;
    }

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell("Spacecraft", "", new CommandRunner("spacecraft"))
                .commandLoop();
    }

}
