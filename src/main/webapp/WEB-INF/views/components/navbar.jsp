<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="fixed top-0 left-0 right-0 z-50 bg-white border-b border-zinc-100 px-6 py-3">
    <div class="max-w-7xl mx-auto flex items-center justify-between">

        <!-- Logo -->
        <a href="${pageContext.request.contextPath}/inicio"
           class="flex items-center gap-2 text-zinc-900 font-semibold text-lg tracking-tight">
            <span class="text-2xl">🐾</span>
            <span>PetCare</span>
        </a>

        <!-- Links -->
        <div class="flex items-center gap-1">
            <a href="${pageContext.request.contextPath}/duenos"
               class="px-4 py-2 text-sm text-zinc-500 hover:text-zinc-900 hover:bg-zinc-50 rounded-lg transition-all">
                Dueños
            </a>
            <a href="${pageContext.request.contextPath}/mascotas"
               class="px-4 py-2 text-sm text-zinc-500 hover:text-zinc-900 hover:bg-zinc-50 rounded-lg transition-all">
                Mascotas
            </a>
            <a href="${pageContext.request.contextPath}/veterinarios"
               class="px-4 py-2 text-sm text-zinc-500 hover:text-zinc-900 hover:bg-zinc-50 rounded-lg transition-all">
                Veterinarios
            </a>
            <a href="${pageContext.request.contextPath}/citas"
               class="px-4 py-2 text-sm text-zinc-500 hover:text-zinc-900 hover:bg-zinc-50 rounded-lg transition-all">
                Citas
            </a>
        </div>

        <!-- Usuario + Logout -->
        <div class="flex items-center gap-3">
            <span class="text-sm text-zinc-400">
                Dr. ${sessionScope.veterinarioLogueado.nombre}
            </span>
            <a href="${pageContext.request.contextPath}/logout"
               class="px-4 py-2 text-sm text-zinc-500 hover:text-red-500 hover:bg-red-50 rounded-lg transition-all">
                Salir
            </a>
        </div>
    </div>
</nav>