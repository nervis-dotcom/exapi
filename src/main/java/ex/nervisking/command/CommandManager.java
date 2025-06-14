package ex.nervisking.command;

import ex.nervisking.ExApi;
import ex.nervisking.ModelManager.Logger;
import ex.nervisking.utils.UtilsManagers;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@SuppressWarnings("CallToPrintStackTrace")
public class CommandManager {

    private final JavaPlugin plugin;
    private final UtilsManagers utilsManagers;

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.utilsManagers = ExApi.getUtilsManagers();
    }

    public void registerCommand(Command command) {
        String name = command.getName();
        PluginCommand pluginCommand = createPluginCommand(name);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
            if (!command.getAliases().isEmpty()) {
                pluginCommand.setAliases(command.getAliases());
            }
            if (!command.getDescription().isEmpty()) {
                pluginCommand.setDescription(command.getDescription());
            }
            if (command.getPermission()) {
                pluginCommand.setPermission(plugin.getName().toLowerCase() + ".command." + command.getName());
            }

            registerBukkitCommand(pluginCommand);
        } else {
            utilsManagers.sendLogger(Logger.WARNING,"No se pudo registrar el comando: " + name);
        }
    }

    public void registerCommand(CustomCommand command) {
        String name = command.getName();
        PluginCommand pluginCommand = createPluginCommand(name);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
            if (!command.getAliases().isEmpty()) {
                pluginCommand.setAliases(command.getAliases());
            }
            if (!command.getDescription().isEmpty()) {
                pluginCommand.setDescription(command.getDescription());
            }
            if (command.getPermission()) {
                pluginCommand.setPermission(plugin.getName().toLowerCase() + ".command." + command.getName());
            }

            registerBukkitCommand(pluginCommand);
        } else {
            utilsManagers.sendLogger(Logger.WARNING,"No se pudo registrar el comando: " + name);
        }
    }

    public void registerCommand(MyCommand command) {
        String name = command.getName();
        PluginCommand pluginCommand = createPluginCommand(name);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
            if (!command.getAliases().isEmpty()) {
                pluginCommand.setAliases(command.getAliases());
            }
            if (!command.getDescription().isEmpty()) {
                pluginCommand.setDescription(command.getDescription());
            }
            if (command.getPermission()) {
                pluginCommand.setPermission(plugin.getName().toLowerCase() + ".command." + command.getName());
            }

            registerBukkitCommand(pluginCommand);
        } else {
            utilsManagers.sendLogger(Logger.WARNING,"No se pudo registrar el comando: " + name);
        }
    }

    public void registerCommand(ExCommand command) {
        String name = command.getName();
        PluginCommand pluginCommand = createPluginCommand(name);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(command);
            pluginCommand.setTabCompleter(command);
            if (!command.getAliases().isEmpty()) {
                pluginCommand.setAliases(command.getAliases());
            }
            if (!command.getDescription().isEmpty()) {
                pluginCommand.setDescription(command.getDescription());
            }
            if (command.getPermission()) {
                pluginCommand.setPermission(plugin.getName().toLowerCase() + ".command." + command.getName());
            }

            registerBukkitCommand(pluginCommand);
        } else {
            utilsManagers.sendLogger(Logger.WARNING,"No se pudo registrar el comando: " + name);
        }
    }

    private PluginCommand createPluginCommand(String name) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);
            return constructor.newInstance(name, plugin);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void registerBukkitCommand(PluginCommand command) {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(plugin.getName().toLowerCase(), command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

