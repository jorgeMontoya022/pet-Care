<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>PetCare — Nuevo Dueño</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style> body { font-family: 'DM Sans', sans-serif; } </style>
</head>
<body class="min-h-screen bg-zinc-50">

<%@ include file="../components/navbar.jsp" %>

<main class="max-w-2xl mx-auto px-6 pt-24 pb-12">

    <div class="mb-8">
        <a href="${pageContext.request.contextPath}/duenos"
           class="text-sm text-zinc-400 hover:text-zinc-600 transition-colors">
            ← Volver a dueños
        </a>
        <h1 class="text-2xl font-semibold text-zinc-900 tracking-tight mt-3">Nuevo dueño</h1>
    </div>

    <!-- Error -->
    <% if (request.getAttribute("error") != null) { %>
    <div class="mb-4 px-4 py-3 bg-red-50 border border-red-100 rounded-xl text-sm text-red-600">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <div class="bg-white border border-zinc-100 rounded-2xl shadow-sm p-8">
        <form action="${pageContext.request.contextPath}/duenos" method="post" class="space-y-5">

            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Cédula *</label>
                <input type="text" name="cedula" required
                       value="${dueno.cedula}"
                       placeholder="Ej: 1097032932"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl
                                  focus:outline-none focus:ring-2 focus:ring-zinc-900 focus:border-transparent
                                  placeholder:text-zinc-300 transition-all"/>
            </div>

            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Nombre completo *</label>
                <input type="text" name="nombre" required
                       value="${dueno.nombre}"
                       placeholder="Ej: Juan Pérez"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl
                                  focus:outline-none focus:ring-2 focus:ring-zinc-900 focus:border-transparent
                                  placeholder:text-zinc-300 transition-all"/>
            </div>

            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Teléfono</label>
                <input type="text" name="telefono"
                       value="${dueno.telefono}"
                       placeholder="Ej: 3001234567"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl
                                  focus:outline-none focus:ring-2 focus:ring-zinc-900 focus:border-transparent
                                  placeholder:text-zinc-300 transition-all"/>
            </div>

            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">Correo electrónico</label>
                <input type="email" name="correo"
                       value="${dueno.correo}"
                       placeholder="Ej: juan@correo.com"
                       class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl
                                  focus:outline-none focus:ring-2 focus:ring-zinc-900 focus:border-transparent
                                  placeholder:text-zinc-300 transition-all"/>
            </div>

            <div class="flex gap-3 pt-2">
                <button type="submit"
                        class="flex-1 py-2.5 bg-zinc-900 text-white text-sm font-medium rounded-xl
                                   hover:bg-zinc-700 active:scale-95 transition-all">
                    Registrar dueño
                </button>
                <a href="${pageContext.request.contextPath}/duenos"
                   class="flex-1 py-2.5 text-center text-sm text-zinc-500 border border-zinc-200
                              rounded-xl hover:bg-zinc-50 transition-all">
                    Cancelar
                </a>
            </div>

        </form>
    </div>
</main>
</body>
</html>