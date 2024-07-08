package io.doraemon.option;

final class ExtendedOptions extends Options {
	public static void main(String[] args) {
		Options options = Options.parseCmdLine(ExtendedOptions.class, args);
		System.out.println(options);
	}
}
