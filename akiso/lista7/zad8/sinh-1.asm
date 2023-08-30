section .data
    x   dd  16.0
    z   dd  0

section .text
    global _start

_start:
    finit

    fld     dword[x]
    fld     st0
    fmul
    fld1
    fadd
    fsqrt
    
    fld     dword[x]
    fadd

    fld1
    fxch    st1
    fyl2x

    fldl2e

    fdivr   st1

    fst     dword[z]

    mov     ebx, 0
    mov     eax, 1
    int     0x80