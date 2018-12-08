package com.julianmartinez.avoris.model.messages;

import com.julianmartinez.avoris.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRS {

    private Long id;
    private String name;

    public static UserRS from(User user){
        if (user == null) return null;
        return new UserRS(user.getId(), user.getName());
    }

}
