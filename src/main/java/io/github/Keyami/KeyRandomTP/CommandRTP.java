package io.github.Keyami.KeyRandomTP;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class CommandRTP implements CommandExecutor {

    KeyRandomTP plugin = (KeyRandomTP) Bukkit.getPluginManager().getPlugin("KeyRandomTP");

    /*

        RTP -> Random Teleport Command
        This function takes in the players current location and world, then it compares them to the config settings.
        It uses a number randomizer to find values to teleport the player to based on the minimum and maximum values found in the
        config file.

     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            //Get the range of coordinates from the config file.
            int maxX = plugin.getConfig().getInt("MaxX");
            int minX = plugin.getConfig().getInt("MinX");

            int maxZ = plugin.getConfig().getInt("MaxZ");
            int minZ = plugin.getConfig().getInt("MinZ");

            //Randomize the numbers that you'll TP to.
            Random rand = new Random();
            int rangeX =  maxX - minX + 1;
            int newX =  rand.nextInt(rangeX) + minX;

            //Randomize the Z value here.
            int rangeZ =  maxZ - minZ + 1;
            int newZ =  rand.nextInt(rangeZ) + minZ;

            //Create that location at the coords.
            Location current = player.getLocation();
            Location teleport = new Location(player.getWorld(),newX,current.getY(),newZ);

            //This finds the highest block at the coordinates. (So players don't suffocate or spawn in the air.)
            int y = player.getWorld().getHighestBlockAt(newX,newZ).getY();
            Block b = player.getWorld().getBlockAt(newX, y, newZ);

            //Using the info we found, we check if the block is a no spawn block.
            if (b.getType() != Material.AIR && b.getType() != Material.WATER && b.getType() != Material.SNOW && b.getType() != Material.SNOW_BLOCK
                    && b.getType() != Material.ICE && b.getType() != Material.PACKED_ICE) {

                //If not, then the Y coordinate is deemed safe and saved in the teleport location variable. (The +3 is to avoid suffocating in walls.)
                teleport.setY(y+3);
                player.teleport(teleport);

            } else {
                //If the Y is deemed unsafe, then we break the command and restart.
                player.performCommand("rtp");
                return false;
            }

         } else {
            plugin.getLogger().info("Only players can execute this command!");
            return false;
        }
        return true;
    }
}
