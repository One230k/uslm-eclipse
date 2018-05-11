package org.one230k.eclipse;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class UslmPlugin extends AbstractUIPlugin {

	private static UslmPlugin plugin = new UslmPlugin();
	
	public static UslmPlugin getPlugin() {
		return plugin;
	}
	
	public static void logInfo(String msg) {
		Status status = new Status(Status.INFO, "org.one230k.uslm", msg);
		plugin.getLog().log(status);
	}
	
	public static void logError(String msg, Exception e) {
		Status status = new Status(Status.ERROR, "org.one230k.uslm", Status.OK, msg, e);
		plugin.getLog().log(status);
	}
}
