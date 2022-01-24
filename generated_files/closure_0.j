.class closure_0
.super java/lang/Object
.implements closure_interface_int_int_int
.field public sl Lf0;
.method public <init>()V
	 aload_0
	 invokenonvirtual java/lang/Object/<init>()V
	 return
.end method
.method public apply(II)I
	.limit locals 5
	.limit stack 256
	new f1
	dup
	invokespecial f1/<init>()V
	dup
	aload_0
	getfield closure_0/sl Lf0;
	putfield f1/sl Lf0;
	dup
	iload 1
	putfield f1/x0 I
	dup
	iload 2
	putfield f1/x1 I
	astore 4
	;-----Id compile-----
	aload 4 
	getfield f1/x0 I
	;-----Id compile-----
	aload 4 
	getfield f1/x1 I
	;-----Id compile-----
	aload 4 
	getfield f1/x0 I
	;-----Id compile-----
	aload 4 
	getfield f1/x1 I
	idiv
	imul
	isub
	ireturn
.end method
