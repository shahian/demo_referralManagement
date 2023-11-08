package com.haytech.demo_referralmanagement.service.intrface;

import com.haytech.demo_referralmanagement.model.base.BaseDTO;
import com.haytech.demo_referralmanagement.model.dto.FanavaranPolicyDTO;

public interface FanavaranPolicyService {
    BaseDTO getAllFanavaranPolicies(int page, int size);

    BaseDTO getFanavaranPolicyById(Long id);

    BaseDTO createFanavaranPolicy(FanavaranPolicyDTO fanavaranPolicyDTO);

    BaseDTO updateFanavaranPolicy(Long id, FanavaranPolicyDTO fanavaranPolicyDTO);

    BaseDTO deleteFanavaranPolicy(Long id);
}
