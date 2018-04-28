package controller;

import model.Employee;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/employees")
public class EmployeeController {

    // 创建线程安全的Map
    static Map<Long, Employee> employeeMap = Collections.synchronizedMap(new HashMap<Long, Employee>());

    @RequestMapping(value="/", method= RequestMethod.GET)
    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<Employee>(employeeMap.values());
        return employees;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String addEmployee(@ModelAttribute Employee employee) {
        if (employeeMap.containsKey(employee.getId())) {
            return "add fail, the employee has existed";
        }
        employeeMap.put(employee.getId(), employee);
        return "success";
    }

    @RequestMapping(value="/get/{id}", method=RequestMethod.GET)
    public Employee getEmployee(@PathVariable Long id) {
        return employeeMap.get(id);
    }

    @RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        Employee employee1 = employeeMap.get(id);
        if (employee1 != null) {
            employee1.setName(employee.getName());
            employee1.setAge(employee.getAge());
            employee1.setGender(employee.getGender());
            employeeMap.put(id, employee1);
            return "success";
        }
        return "update fail, the employee does not exist!";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeMap.containsKey(id)) {
            employeeMap.remove(id);
            return "success";
        }
        return "delete fail, please verify the id!";
    }
}
