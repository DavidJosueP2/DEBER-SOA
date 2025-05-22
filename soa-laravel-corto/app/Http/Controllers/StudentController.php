<?php

namespace App\Http\Controllers;

use App\Models\Student;
use Illuminate\Http\Request;

class StudentController extends Controller
{
    /**
     * Muestra la lista de estudiantes activos.
     */
    public function index()
    {
        $students = Student::whereNull('deleted_at')->get();
        return view('students.index', compact('students'));
    }

    /**
     * Muestra la vista con el formulario de edición cargado.
     */
    public function edit($id)
    {
        $students = Student::whereNull('deleted_at')->get();
        $student = Student::where('id', $id)
                          ->whereNull('deleted_at')
                          ->firstOrFail();

        return view('students.index', compact('students', 'student'));
    }

    /**
     * Guarda un nuevo estudiante.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'cedula' => 'required|string|max:10|unique:students,cedula',
            'name' => 'required|string|max:100',
            'lastname' => 'required|string|max:100',
            'address' => 'required|string|max:100',
            'telephone' => 'required|string|max:10',
        ]);

        Student::create($validated);

        return redirect()->route('students.index')->with('success', 'Estudiante creado correctamente.');
    }

    /**
     * Actualiza un estudiante existente.
     */
    public function update(Request $request, $id)
    {
        $student = Student::where('id', $id)
                          ->whereNull('deleted_at')
                          ->firstOrFail();

        $validated = $request->validate([
            'cedula' => [
                'sometimes',
                'required',
                'string',
                'max:10',
                function ($attribute, $value, $fail) use ($id) {
                    $exists = Student::where('cedula', $value)
                                     ->whereNull('deleted_at')
                                     ->where('id', '<>', $id)
                                     ->exists();

                    if ($exists) {
                        $fail('La cédula ya está registrada.');
                    }
                },
            ],
            'name' => 'sometimes|required|string|max:100',
            'lastname' => 'sometimes|required|string|max:100',
            'address' => 'sometimes|required|string|max:100',
            'telephone' => 'sometimes|required|string|max:10',
        ]);

        $student->update($validated);

        return redirect()->route('students.index')->with('success', 'Estudiante actualizado correctamente.');
    }

    /**
     * Elimina lógicamente un estudiante.
     */
    public function destroy($id)
    {
        $student = Student::where('id', $id)
                          ->whereNull('deleted_at')
                          ->firstOrFail();

        $student->delete();

        return redirect()->route('students.index')->with('success', 'Estudiante eliminado correctamente.');
    }

    /**
     * Muestra los estudiantes eliminados.
     */
    public function trash()
    {
        $students = Student::onlyTrashed()->get();
        return view('students.index', compact('students'));
    }

    /**
     * Restaura un estudiante eliminado.
     */
    public function restore($cedula)
    {
        $student = Student::onlyTrashed()->where('cedula', $cedula)->firstOrFail();

        $student->restore();

        return redirect()->route('students.index')->with('success', 'Estudiante restaurado correctamente.');
    }
}
