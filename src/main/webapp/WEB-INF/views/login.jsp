<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetCare — Iniciar Sesión</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@300;400;500;600&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'DM Sans', sans-serif; }
    </style>
</head>
<body class="min-h-screen bg-zinc-50 flex items-center justify-center">

<div class="w-full max-w-sm">

    <!-- Logo -->
    <div class="text-center mb-8">
        <span class="text-5xl">🐾</span>
        <h1 class="mt-3 text-2xl font-semibold text-zinc-900 tracking-tight">PetCare</h1>
        <p class="mt-1 text-sm text-zinc-400">Sistema de Gestión Veterinaria</p>
    </div>

    <!-- Card -->
    <div class="bg-white rounded-2xl border border-zinc-100 shadow-sm p-8">

        <h2 class="text-lg font-medium text-zinc-800 mb-6">Iniciar sesión</h2>

        <!-- Error -->
        <% if (request.getAttribute("error") != null) { %>
        <div class="mb-4 px-4 py-3 bg-red-50 border border-red-100 rounded-xl text-sm text-red-600">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>

        <!-- Formulario -->
        <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-4">

            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">
                    Tarjeta Profesional
                </label>
                <input
                        type="text"
                        name="tarjetaProfesional"
                        placeholder="Ej: TP-001"
                        required
                        class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl
                               focus:outline-none focus:ring-2 focus:ring-zinc-900 focus:border-transparent
                               placeholder:text-zinc-300 transition-all"
                />
            </div>

            <div>
                <label class="block text-sm font-medium text-zinc-600 mb-1.5">
                    Contraseña
                </label>
                <input
                        type="password"
                        name="contrasena"
                        placeholder="••••••••"
                        required
                        class="w-full px-4 py-2.5 text-sm bg-zinc-50 border border-zinc-200 rounded-xl
                               focus:outline-none focus:ring-2 focus:ring-zinc-900 focus:border-transparent
                               placeholder:text-zinc-300 transition-all"
                />
            </div>

            <button
                    type="submit"
                    class="w-full py-2.5 bg-zinc-900 text-white text-sm font-medium rounded-xl
                           hover:bg-zinc-700 active:scale-95 transition-all mt-2">
                Entrar
            </button>

        </form>
    </div>

    <p class="text-center text-xs text-zinc-300 mt-6">PetCare © 2025</p>
</div>

</body>
</html>