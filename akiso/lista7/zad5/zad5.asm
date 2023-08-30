%include    'print_eax.asm'

section .text
    global _start

_start:
    mov ebx, 11
    mov ecx, 13

    call    multiply
    call    print_eax
    
    mov eax, 1
    xor ebx, ebx
    int 0x80

; function multiplies ebx * ecx and stores result in eax
multiply:
    mov     eax, 0
    test    ecx, 1
    jz      .mul_loop
    add     eax, ebx
.mul_loop:
    shl     ebx, 1
    shr     ecx, 1
    cmp     ecx, 0
    je      .finish_mul
    test    ecx, 1
    jz      .mul_loop
    add     eax, ebx
    jmp     .mul_loop
.finish_mul:
    ret