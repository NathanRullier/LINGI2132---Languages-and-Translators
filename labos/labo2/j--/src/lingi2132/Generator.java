package lingi2132;

import java.util.ArrayList;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;



public class Generator extends GlobalGenerator{
	private String outputDir;
	public Generator(String outputDir) {
		this.outputDir = outputDir;
	}
	public void generateClass() {
		CLEmitter output = new CLEmitter(true);
		output.destinationDir(outputDir);
		
		ArrayList<String> accessFlags = new ArrayList<>();
		
		accessFlags.add("super");
		accessFlags.add("public");
		
		output.addClass(accessFlags , "packageOfClassToGenerate/ClassToGenerate", "java/lang/Object", null, false);
		
		accessFlags.clear();
		accessFlags.add("public");
		accessFlags.add("static");
		
		output.addMethod(accessFlags, "gcd", "(I,I)I ", null, false);

		output.addNoArgInstruction(ILOAD_1);
		output.addBranchInstruction(IFEQ, "whileE");
		output.addLabel("whileB");
		
		output.addNoArgInstruction(ILOAD_0);
		output.addNoArgInstruction(ILOAD_1);
		output.addNoArgInstruction(ISUB);
		
		output.addBranchInstruction(IFLE, "else");
		
		output.addNoArgInstruction(ISTORE_0);
		
		output.addBranchInstruction(GOTO, "ifE");
		
		output.addLabel("else");

		output.addNoArgInstruction(ILOAD_1);
		output.addNoArgInstruction(ILOAD_0);
		output.addNoArgInstruction(ISUB);
		output.addNoArgInstruction(ISTORE_1);
		
		output.addLabel("ifE");
		
		output.addNoArgInstruction(ILOAD_1);
		output.addBranchInstruction(IFNE, "whileB");
		output.addLabel("whileE");
		
		output.addNoArgInstruction(ILOAD_0);
		output.addNoArgInstruction(IRETURN);
		
		
		
		
		
		output.write();
		
	}
	public static void main(String[] args) {
		Generator gen = new Generator(args[0]);
		gen.generateClass();
	}
}	