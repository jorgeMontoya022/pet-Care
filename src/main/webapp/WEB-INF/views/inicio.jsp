<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Inicio</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body {
        font-family: 'DM Sans', sans-serif;
    } </style>
</head>
<body class="min-h-screen bg-zinc-50">

<%@ include file="components/navbar.jsp" %>

<main class="max-w-7xl mx-auto px-6 pt-24 pb-12">

    <!-- Saludo -->
    <div class="mb-10">
        <h1 class="text-3xl font-semibold text-zinc-900 tracking-tight">
            Bienvenido, Dr. ${sessionScope.veterinarioLogueado.nombre} 👋
        </h1>
        <p class="mt-1 text-zinc-400 text-sm">¿Qué vas a gestionar hoy?</p>
    </div>

    <!-- Cards de navegación -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">

        <a href="${pageContext.request.contextPath}/duenos"
           class="group bg-white border border-zinc-100 rounded-2xl p-6 hover:border-zinc-300
                      hover:shadow-md transition-all">
            <div class="text-3xl mb-4">👤</div>
            <h3 class="font-medium text-zinc-900 group-hover:text-zinc-700">Dueños</h3>
            <p class="text-xs text-zinc-400 mt-1">Registrar y gestionar clientes</p>
        </a>

        <a href="${pageContext.request.contextPath}/mascotas"
           class="group bg-white border border-zinc-100 rounded-2xl p-6 hover:border-zinc-300
                      hover:shadow-md transition-all">
            <div class="text-3xl mb-4">🐶</div>
            <h3 class="font-medium text-zinc-900 group-hover:text-zinc-700">Mascotas</h3>
            <p class="text-xs text-zinc-400 mt-1">Registro de pacientes</p>
        </a>

        <a href="${pageContext.request.contextPath}/citas"
           class="group bg-white border border-zinc-100 rounded-2xl p-6 hover:border-zinc-300
                      hover:shadow-md transition-all">
            <div class="text-3xl mb-4">📅</div>
            <h3 class="font-medium text-zinc-900 group-hover:text-zinc-700">Citas</h3>
            <p class="text-xs text-zinc-400 mt-1">Agenda y programación</p>
        </a>

        <a href="${pageContext.request.contextPath}/veterinarios"
           class="group bg-white border border-zinc-100 rounded-2xl p-6 hover:border-zinc-300
                      hover:shadow-md transition-all">
            <div class="text-3xl mb-4">🩺</div>
            <h3 class="font-medium text-zinc-900 group-hover:text-zinc-700">Veterinarios</h3>
            <p class="text-xs text-zinc-400 mt-1">Equipo médico</p>
        </a>

    </div>
</main>

</body>
</html>