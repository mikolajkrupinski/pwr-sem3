; functions prints decimal number stored in eax and adds new line
print_eax:
    push    eax
    push    ebx
    push    ecx
    push    edx
    push    esi

    mov     ecx, 0

.divide_loop:
    inc     ecx
    mov     edx, 0
    mov     esi, 10
    idiv    esi
    add     edx, 48
    push    edx
    cmp     eax, 0
    jnz     .divide_loop

.print_loop:
    dec     ecx
    mov     eax, esp
    push    ecx

    mov     edx, 1
    mov     ecx, eax
    mov     ebx, 1
    mov     eax, 4
    int     80h

    pop     ecx
    pop     eax
    cmp     ecx, 0
    jnz     .print_loop

.exit:
    mov     eax, 0Ah
    push    eax
    mov     edx, 1
    mov     ecx, esp
    mov     ebx, 1
    mov     eax, 4
    int     80h

    pop     eax
    pop     esi
    pop     edx
    pop     ecx
    pop     ebx
    pop     eax

    ret
