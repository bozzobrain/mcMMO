import java.util.logging.Level;
import java.util.logging.Logger;
//=====================================================================
//Class:	vminecraftListener
//Use:		The listener to catch incoming chat and commands
//Author:	nossr50, TrapAlice, cerevisiae
//=====================================================================
public class vminecraftListener extends PluginListener {
	protected static final Logger log = Logger.getLogger("Minecraft");
	
	//=====================================================================
	//Function:	disable
	//Input:	None
	//Output:	None
	//Use:		Disables vminecraft, but why would you want to do that? ;)
	//=====================================================================
	public void disable() {
		log.log(Level.INFO, "vminecraft disabled");
	}
	
	//=====================================================================
	//Function:	onChat
	//Input:	Player player: The player calling the command
	//			String message: The message to color
	//Output:	boolean: If the user has access to the command
	//					 and it is enabled
	//Use:		Checks for quote, rage, and colors
	//=====================================================================
    public boolean onChat(Player player, String message){

    	//Quote (Greentext)
    	if (message.startsWith(">"))
    		vminecraftChat.quote(player, message);
        	
        //Rage (FFF)
        else if (message.startsWith("FFF"))
        	vminecraftChat.rage(player, message);
    	
    	//Send through quakeColors otherwise
        else
        	vminecraftChat.quakeColors(player, message);

    	return false;
    }
    
	//=====================================================================
	//Function:	onCommand
	//Input:	Player player: The player calling the command
	//			String[] split: The arguments
	//Output:	boolean: If the user has access to the command
	//					 and it is enabled
	//Use:		Checks for exploits and runs the commands
	//=====================================================================
	public boolean onCommand(Player player, String[] split) {

        //Explot fix on /modify
	    if(split[0].equals("/modify") && split[2].equals("commands")) {
	        return false;
	    }

        //Copy the arguments into their own array.
	    String[] args = new String[split.length - 1];
        System.arraycopy(split, 1, args, 0, args.length);

        //Return the results of the command
        return vminecraftCommands.cl.call(split[0], player, args);
        
	}
    
	//=====================================================================
	//Function:	onHealthChange
	//Input:	Player player: The player calling the command
	//			int oldValue: The old health value;
	//			int newValue: The new health value
	//Output:	boolean: If the user has access to the command
	//					 and it is enabled
	//Use:		Checks for exploits and runs the commands
	//=====================================================================
    public boolean onHealthChange(Player player,int oldValue,int newValue){
    	if (player.getHealth() != vminecraftSettings.getInstance().ezModoHealth() && vminecraftSettings.getInstance().isEzModo(player.getName())) {
                player.setHealth(vminecraftSettings.getInstance().ezModoHealth());

            }
     else if (vminecraftSettings.getInstance().globalmessages() && player.getHealth() < 1) {
         vminecraftChat.gmsg(Colors.Gray + player.getName() + " is no more");
            }
            return false; 
    	}
}