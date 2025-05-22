<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Matrículas</title>
</head>
<body>
    <h1>Matrículas</h1>


    {{-- Filtros --}}
    <form method="GET" action="{{ route('enrolments.index') }}">
        <label for="student_id">Filtrar por estudiante:</label>
        <select name="student_id" id="student_id">
            <option value="">-- Todos --</option>
            @foreach($students as $student)
                <option value="{{ $student->id }}" {{ request('student_id') == $student->id ? 'selected' : '' }}>
                    {{ $student->name }} {{ $student->lastname }}
                </option>
            @endforeach
        </select>

        <label for="subject_id">Filtrar por materia:</label>
        <select name="subject_id" id="subject_id">
            <option value="">-- Todas --</option>
            @foreach($subjects as $subject)
                <option value="{{ $subject->id }}" {{ request('subject_id') == $subject->id ? 'selected' : '' }}>
                    {{ $subject->subject_name }}
                </option>
            @endforeach
        </select>

        <button type="submit">Filtrar</button>
    </form>

    <hr>

    {{-- Formulario para registrar matrícula --}}
    <h2>Registrar Nueva Matrícula</h2>
    <form method="POST" action="{{ route('enrolments.store') }}">
        @csrf
        <label for="student_id_form">Estudiante:</label>
        <select name="student_id" id="student_id_form" required>
            <option value="">-- Seleccione --</option>
            @foreach($students as $student)
                <option value="{{ $student->id }}">
                    {{ $student->name }} {{ $student->lastname }}
                </option>
            @endforeach
        </select>

        <label for="subject_id_form">Materia:</label>
        <select name="subject_id" id="subject_id_form" required>
            <option value="">-- Seleccione --</option>
            @foreach($subjects as $subject)
                <option value="{{ $subject->id }}">
                    {{ $subject->subject_name }}
                </option>
            @endforeach
        </select>

        <button type="submit">Guardar Matrícula</button>
    </form>

    <hr>

    {{-- Tabla de matrículas --}}
    <h2>Listado de Matrículas</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>Id Matricula</th>
                <th>Estudiante</th>
                <th>Materia</th>
                <th>Actualizar</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            @forelse($enrolments as $enrolment)
                <tr>
                    <td>{{ $enrolment->id }}</td>
                    <td>{{ $enrolment->student->name }} {{ $enrolment->student->lastname }}</td>
                    <td>{{ $enrolment->subject->subject_name }}</td>

                    {{-- Formulario para actualizar matrícula --}}
                    <td>
                        <form action="{{ route('enrolments.update', $enrolment->id) }}" method="POST">
                            @csrf
                            @method('PUT')

                            <select name="student_id">
                                @foreach($students as $student)
                                    <option value="{{ $student->id }}" {{ $enrolment->student_id == $student->id ? 'selected' : '' }}>
                                        {{ $student->name }} {{ $student->lastname }}
                                    </option>
                                @endforeach
                            </select>

                            <select name="subject_id">
                                @foreach($subjects as $subject)
                                    <option value="{{ $subject->id }}" {{ $enrolment->subject_id == $subject->id ? 'selected' : '' }}>
                                        {{ $subject->subject_name }}
                                    </option>
                                @endforeach
                            </select>

                            <button type="submit">Actualizar</button>
                        </form>
                    </td>

                    {{-- Eliminar matrícula --}}
                    <td>
                        <form action="{{ route('enrolments.destroy', $enrolment->id) }}" method="POST" onsubmit="return confirm('¿Eliminar matrícula?')">
                            @csrf
                            @method('DELETE')
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            @empty
                <tr><td colspan="4">No hay matrículas registradas.</td></tr>
            @endforelse
        </tbody>
    </table>
    <br>
    {{-- Mensajes de sesión --}}
    @if(session('success'))
        <div style="color: green;">{{ session('success') }}</div>
    @endif
    @if(session('error'))
        <div style="color: red;">{{ session('error') }}</div>
    @endif
</body>
</html>
