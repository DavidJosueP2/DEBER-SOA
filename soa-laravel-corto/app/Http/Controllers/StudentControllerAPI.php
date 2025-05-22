<?php

namespace App\Http\Controllers;

use App\Models\Student;
use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Validation\ValidationException;

class StudentControllerAPI extends Controller
{
    /**
     * Get all active students (excluding soft-deleted).
     */
    public function index()
    {
        $students = Student::whereNull('deleted_at')->get();

        if ($students->isEmpty()) {
            return response()->json(['message' => 'No students found.'], 404);
        }

        return response()->json($students, 200);
    }

    /**
     * Get a student by ID (only active students).
     */
    public function showById($id)
    {
        $student = Student::where('id', $id)
                    ->whereNull('deleted_at')
                    ->first();

        if (!$student) {
            return response()->json(['message' => 'Student not found.'], 404);
        }

        return response()->json($student);
    }

    /**
     * Get a student by cedula if not deleted.
     */
    public function showByCedula($cedula)
    {
        $student = Student::where('cedula', $cedula)
                          ->whereNull('deleted_at')
                          ->first();

        if (!$student) {
            return response()->json(['message' => 'Student not found.'], 404);
        }

        return response()->json($student, 200);
    }

    /**
     * Create a new student.
     */
    public function store(Request $request)
    {
        try {
            $validated = $request->validate([
                'cedula' => 'required|string|max:10|unique:students,cedula',
                'name' => 'required|string|max:100',
                'lastname' => 'required|string|max:100',
                'address' => 'required|string|max:100',
                'telephone' => 'required|string|max:10',
            ]);

            $student = Student::create($validated);

            return response()->json($student, 201);
        } catch (ValidationException $e) {
            return response()->json([
                'message' => 'Validation failed.',
                'errors' => $e->errors()
            ], 422);
        } catch (\Exception $e) {
            return response()->json(['message' => 'Server error.'], 500);
        }
    }

    /**
     * Update an existing student.
     */
    public function update(Request $request, $id)
    {
        $student = Student::where('id', $id)
                        ->whereNull('deleted_at')
                        ->first();

        if (!$student) {
            return response()->json(['message' => 'Student not found.'], 404);
        }

        try {
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
                            return $fail('The cedula has already been taken.');
                        }
                    },
                ],
                'name' => 'sometimes|required|string|max:100',
                'lastname' => 'sometimes|required|string|max:100',
                'address' => 'sometimes|required|string|max:100',
                'telephone' => 'sometimes|required|string|max:10',
            ]);
            $student->update($validated);

            return response()->json($student, 200);
        } catch (ValidationException $e) {
            return response()->json([
                'message' => 'Validation failed.',
                'errors' => $e->errors()
            ], 422);
        } catch (\Exception $e) {
            return response()->json(['message' => 'Server error.'], 500);
        }
    }
    
    /**
     * Soft delete a student.
     */
    public function destroy($id)
    {
        $student = Student::where('id', $id)
                          ->whereNull('deleted_at')
                          ->first();

        if (!$student) {
            return response()->json(['message' => 'Student not found.'], 404);
        }

        try {
            $student->delete();

            return response()->json(['message' => 'Student deleted successfully.'], 200);
        } catch (\Exception $e) {
            return response()->json(['message' => 'Server error.'], 500);
        }
    }

    /**
     * (Optional) Show all soft-deleted students.
     */
    public function trash()
    {
        $students = Student::onlyTrashed()->get();

        if ($students->isEmpty()) {
            return response()->json(['message' => 'No trashed students found.'], 404);
        }

        return response()->json($students, 200);
    }

    /**
     * (Optional) Restore a soft-deleted student.
     */
    public function restore($cedula)
    {
        $student = Student::onlyTrashed()->where('cedula', $cedula)->first();

        if (!$student) {
            return response()->json(['message' => 'Student not found in trash.'], 404);
        }

        try {
            $student->restore();

            return response()->json(['message' => 'Student restored successfully.'], 200);
        } catch (\Exception $e) {
            return response()->json(['message' => 'Server error.'], 500);
        }
    }
}
