<?php

namespace App\Http\Controllers;

use App\Models\Student;
use Illuminate\Http\Request;

class StudentController extends Controller
{
    public function index()
    {
        return response()->json(Student::get());
    }

    public function save(Request $request)
    {
        $saved = Student::create([
            "name" => $request->input('name'),
            "email" => $request->input('email')
        ]);

        return response()->json($saved);
    }

    public function show($id)
    {
        $student = Student::find($id);
        if (!$student) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        return response()->json($student);
    }


    public function update(Request $request, $id)
    {
        $student = Student::find($id);
        if (!$student) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        $student->name  = $request->input('name');
        $student->email = $request->input('email');
        $student->save();

        return response()->json($student);
    }

    public function delete($id)
    {
        $student = Student::find($id);
        if (!$student) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        $student->delete();
        return response()->json(['message' => 'Eliminado correctamente']);
    }

    public function withEnrollments($id)
    {
        $student = Student::with("enrollments")->find($id);

        if (!$student) {
            return response()->json(['error' => 'No encontrado'], 404);
        }

        return response()->json($student);
    }
}
