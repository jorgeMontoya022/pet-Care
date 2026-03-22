<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Nuevo Veterinario</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">
<%@ include file="../components/navbar.jsp" %>
<main class="max-w-2xl mx-auto px-6 pt-24 pb-12">
    <div class="mb-8">
        <a href="${pageContext.request.contextPath}/veterinarios" class="text-sm text-zinc-400 hover:text-zinc-600 transition-colors">← Volver</a>
        <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight mt-3">Nuevo veterinario</h1>
    </div>
    <% if (request.getAttribute("error") != null) { %>
    <div class="mb-4 px-4 py-3 bg-red-50 border border-red-100 rounded-xl text-sm text-red-600"><%= request.getAttribute("error") %></div>
    <% } %>
    <div class="bg-white border border-zinc-100 rounded-2xl shadow-sm p-8">
        <form action="${pageContext.request.contextPath}/veterinarios" method="post" class="space-y-5">
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Nombre completo *</label>
                <input type="text" name="nombre" required value="${veterinario.nombre}" placeholder="Ej: Dr. Carlos López"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Especialidad</label>
                <select name="especialidad"
                        class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 transition-all">
                    <option value="General">General</option>
                    <option value="Cirujano">Cirujano</option>
                    <option value="Dermatólogo">Dermatólogo</option>
                    <option value="Cardiólogo">Cardiólogo</option>
                    <option value="Odontólogo">Odontólogo</option>
                </select>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Tarjeta Profesional *</label>
                <input type="text" name="tarjetaProfesional" required value="${veterinario.tarjetaProfesional}" placeholder="Ej: TP-001"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
            </div>
            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Contraseña *</label>
                <input type="password" name="contrasena" required placeholder="••••••••"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-zinc-900 placeholder:text-zinc-300 transition-all"/>
            </div>
            <div class="flex gap-3 pt-2">
                <button type="submit" class="flex-1 py-2.5 bg-zinc-900 text-white text-sm font-medium rounded-xl hover:bg-zinc-700 active:scale-95 transition-all">Registrar veterinario</button>
                <a href="${pageContext.request.contextPath}/veterinarios" class="flex-1 py-2.5 text-center text-sm text-zinc-500 border border-zinc-200 rounded-xl hover:bg-zinc-50 transition-all">Cancelar</a>
            </div>
        </form>
    </div>
</main>
</body>
</html>