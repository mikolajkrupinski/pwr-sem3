section .data
    x   dd  0.5
    z   dd  0
    tmp dd  0

section .text
    global _start

_start:
    finit

    fld     dword[x]
    call    ex
    fst     dword[z]
    finit
    fld     dword[x]
    fchs
    call    ex
    fld     dword[z]
    fsubr
    fld1
    fld1
    fadd
    fdiv

    jmp     exit

ex:
    fldl2e
    fmul

    fld1
    fscale

    fxch    st1
    fld     st0
    fld1
    fxch    st1
    fprem
    fld     st2
    fsub    st1
    fxch    st1

    f2xm1
    fld1
    fadd

    fld     st4
    fmul

    ret


exit:
    mov     ebx, 0
    mov     eax, 1
    int     0x80