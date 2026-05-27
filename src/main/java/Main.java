class Main {
	public static void main(String[] args) {
		String view_mode = "console";

		if (args.length > 1) {
			System.err.println("ERROR: too much arguments");
			return ;
		} else if (args.length == 0) {
			System.out.println("WARNING: default mode \"console\" set");
		} else {
			switch (args[0]) {
				case "console"	-> view_mode = "console";
				case "gui"		-> view_mode = "gui";
				default			-> {
					System.err.println("ERROR: unknown view mode selected");
					return ;
				}
			}
			System.out.println("\"" + view_mode + "\" MODE SELECTED");
		}

		// SwingyController controller = new SwingyController(view_mode);
	}
}
