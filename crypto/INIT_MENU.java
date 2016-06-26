public enum INIT_MENU {
	CREATE, DECODE, FILE_LIST, EXIT;
	
	private static INIT_MENU[] list = INIT_MENU.values();
	
	public static INIT_MENU intToINIT_MENU(int n) {
		for(int i=0; i<list.length; i++) {
			if(list[i].ordinal()+1 == n) {
				return list[i];
			}
		}
		return null;
	}
}
