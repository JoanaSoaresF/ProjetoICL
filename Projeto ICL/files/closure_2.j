.class closure_2
.super java/lang/Object
.implements closure_interface_int_int_bool
.field public sl Lf0;
.method public <init>()V
	 aload_0
	 invokenonvirtual java/lang/Object/<init>()V
	 return
.end method
.method public apply(II)I
	.limit locals 5
	.limit stack 256
	new f3
	dup
	invokespecial f3/<init>()V
	dup
	aload_0
	getfield closure_2/sl Lf0;
	putfield f3/sl Lf0;
	dup
	iload 1
	putfield f3/x0 I
	dup
	iload 2
	putfield f3/x1 I
	astore 4
	;-----Id compile-----
	aload 4 
	getfield f3/x0 I
	;-----Id compile-----
	aload 4 
	getfield f3/x0 I
	imul
	;-----Id compile-----
	aload 4 
	getfield f3/x1 I
	;-----Id compile-----
	aload 4 
	getfield f3/x1 I
	imul
	iadd
	sipush 32767
	isub
	ifle L0
	sipush 0
	goto L1
	L0:
 sipush 1
	L1:
	ireturn
.end method
